# -*- coding: utf-8 -*-
"""
A plugin to display hudson results in the timeline and provide a nav-link
"""

import time
import calendar
import feedparser
from trac.core import *
from trac.config import Option, BoolOption
from trac.util import Markup, format_datetime
from trac.web.chrome import INavigationContributor, ITemplateProvider, add_stylesheet
from trac.Timeline import ITimelineEventProvider

class HudsonTracPlugin(Component):
    implements(INavigationContributor, ITimelineEventProvider, ITemplateProvider)

    disp_sub = BoolOption('hudson', 'display_subprojects', 'false',
		          'Display status of subprojects in timeline too')
    feed_url = Option('hudson', 'feed_url', 'http://localhost/hudson/rssAll',
		      'The url of the hudson rss feed containing the build ' +
		      'statuses. This must be an absolute url.')
    nav_url  = Option('hudson', 'main_page', '/hudson/',
		      'The url of the hudson main page to which the trac nav ' +
		      'entry should link; if empty, no entry is created in ' +
		      'the nav bar. This may be a relative url.')

    # INavigationContributor methods

    def get_active_navigation_item(self, req):
        return 'builds'

    def get_navigation_items(self, req):
	if self.nav_url:
	    yield 'mainnav', 'builds', Markup('<a href="%s">Builds</a>' % self.nav_url)

    # ITemplateProvider methods
    def get_templates_dirs(self):
        return [self.env.get_templates_dir(),
                self.config.get('trac', 'templates_dir')]

    def get_htdocs_dirs(self):
        from pkg_resources import resource_filename
        return [('HudsonTrac', resource_filename(__name__, 'htdocs'))]

    # ITimelineEventProvider methods

    def get_timeline_filters(self, req):
        if req.perm.has_permission('CHANGESET_VIEW'):
            yield ('build', 'Hudson Builds')

    def get_timeline_events(self, req, start, stop, filters):
        if 'build' in filters:
            add_stylesheet(req, 'HudsonTrac/hudsontrac.css')

            feed = feedparser.parse(self.feed_url)

            for entry in feed.entries:
                # Only look at top-level entries
                if not self.disp_sub and entry.title.find(u'»') >= 0:
                    continue

		# check time range
		completed = calendar.timegm(entry.updated_parsed)
		if completed > stop:
		    continue
		if completed < start:
		    break

		# create timeline entry
                if entry.title.find('SUCCESS') >= 0:
                    message = 'Build finished successfully'
                    kind = 'build-successful'
                else:
                    message = 'Build failed'
                    kind = 'build-failed'

                href = entry.link
                title = entry.title
                comment = message + ' at ' + format_datetime(completed)

                yield kind, href, title, completed, None, comment

